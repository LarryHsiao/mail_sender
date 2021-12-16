email_sender_server
---

Simple email sender endpoint for local smtp server. 

fire up
---

```shell
./run.sh 8081
```

Request
---

```shell
curl --request POST \
  --url http://larryhsiao.com:8081/send_email \
  --data '{
	"to":"larry_hsiao@larryhsiao.com",
	"from": "2021@larryhsiao.com",
	"title": "title",
	"content": "<h1>content: 1234</h1>",
	"contentType": "text/html;charset=utf-8"
}'
```