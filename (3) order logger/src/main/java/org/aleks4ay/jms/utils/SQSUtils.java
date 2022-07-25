package org.aleks4ay.jms.utils;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;


public class SQSUtils
{
/*    public static void main(String[] args)
    {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        try {
            sqs.createQueue(QUEUE_NAME);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }

        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody("hello world 3")
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }*/

    public static void sendSqs(String queueName, String message) {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        try {
            sqs.createQueue(queueName);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }

        String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }
}
