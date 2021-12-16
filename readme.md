email_sender_server
---

Simple email sender endpoint for local smtp server. 


Request
---

```shell
curl --request POST \
  --url ${HOST}/send_email \
  --data '{
	"email":"larry_hsiao@cmoney.com.tw",
	"title": "title",
	"content": "<h1>驗證碼: 1234</h1>",
	"contentType": "text/html;charset=utf-8"
}'
```