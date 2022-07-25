package org.aleks4ay.jms.logger;

import org.aleks4ay.jms.utils.Constants;
import org.aleks4ay.jms.utils.S3Utils;
import org.aleks4ay.jms.utils.SQSUtils;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.io.File;
import java.time.LocalDateTime;

@Service
public class JmsReceiver {

    private static final String ORDER_QUEUE_ACCESS = "order.queue.access";
    private static final String ORDER_QUEUE_REJECT = "order.queue.reject";
    private final static String BUCKET_NAME = Constants.BUCKET_NAME;
    private final static String S3_FILE_MAME = Constants.S3_FILE_MAME;
    private static final String QUEUE_NAME = Constants.QUEUE_NAME;

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsReceiver.class);

    @JmsListener(destination = ORDER_QUEUE_ACCESS)
    public void logAccessMessage(ActiveMQTextMessage message){
        LOGGER.info("New access Message received!");
        String newLog = null;
        try {
            newLog = "Accessed order: " + message.toString() + System.lineSeparator() + message.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        String fileFromS3 = S3Utils.getFileFromS3asString(BUCKET_NAME, S3_FILE_MAME);
        fileFromS3 = S3Utils.append(fileFromS3, newLog);

        File file = S3Utils.writeFile(fileFromS3);
        S3Utils.putFileToS3(file, BUCKET_NAME, S3_FILE_MAME);

        SQSUtils.sendSqs(QUEUE_NAME, "File '" + S3_FILE_MAME + "' was changed. Date: " + LocalDateTime.now());
    }

    @JmsListener(destination = ORDER_QUEUE_REJECT)
    public void logRejectedMessage(ActiveMQTextMessage message){
        LOGGER.info("New rejected Message received!");
        String newLog = null;
        try {
            newLog = "Rejected order: " + message.toString() + System.lineSeparator() + message.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        String fileFromS3 = S3Utils.getFileFromS3asString(BUCKET_NAME, S3_FILE_MAME);
        fileFromS3 = S3Utils.append(fileFromS3, newLog);

        File file = S3Utils.writeFile(fileFromS3);
        S3Utils.putFileToS3(file, BUCKET_NAME, S3_FILE_MAME);

        SQSUtils.sendSqs(QUEUE_NAME, "File '" + S3_FILE_MAME + "' was changed. Date: " + LocalDateTime.now());
    }

}
