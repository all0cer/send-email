# Email Microservice

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

This project is an API built using **Java, Java Spring, AWS Simple Email Service, SendGrid, RabbitMQ.**



## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Docker](#docker)


## Installation

1. Clone the repository:

```bash
git clone https://github.com/all0cer/send-email.git
```

2. Install dependencies with Maven

3. Update `application.properties` puting your AWS, RABBITMQ (AMQP), SENDGRID and EMAIL credentials

```yaml
acessKeyId=${AWS_ACESS_KEY_ID}
secretKey=${AWS_SECRET_KEY}
region=${AWS_REGION}

gridkey=${SENDGRID_API_KEY}

spring.rabbitmq.addresses=${RABBITMQ_ADRESS}
spring.rabbitmq.queue=${RABBITMQ_NAME_QUEUE}

from.email=${EMAIL_FROM} #The email that will be used as sender
```
## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

**SEND EMAIL**
```markdown
POST /api/email/send - Send a e-mail from your sender to the destination
```

**BODY**
```json
{
  "to": "example@gmail.com",
  "subject": "test",
  "body": "test"
}
```

## Docker

You also can use the docker image to run the application

pull the image
```bash
docker pull all0cer/send-email:latest
```

## Run the container
Make sure to pass your credentials via environment variables:
```bash
docker run -d --name send-email -e AWS_ACESS_KEY_ID=${AWS_ACESS_KEY_ID} -e AWS_SECRET_KEY=${AWS_SECRET_KEY} -e AWS_REGION=${AWS_REGION} -e SENDGRID_API_KEY=${SENDGRID_API_KEY} -e RABBITMQ_ADRESS=${RABBITMQ_ADRESS} -e RABBITMQ_NAME_QUEUE=${RABBITMQ_NAME_QUEUE} -e EMAIL_FROM=${EMAIL_FROM} all0cer/send-email:latest
```




