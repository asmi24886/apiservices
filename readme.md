###To Register a User
_REQUEST_

POST
`http://localhost:3030/api/user`
```json
{
    "fullname":"Parijat Mukherjee",
    "username":"parijat",
    "password":"parijat",
    "email" : "abc@abc.com"
}
```

###To get the token
_REQUEST_

POST
`http://localhost:3030/api/auth`

```json
{
    "username":"admin",
    "password":"admin"
}
```
_RESPONSE_

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwNDk4ODUyODM2NiwiZXhwIjoxNTA1NTkzMzI4fQ.YgsC5rRM2-JMuSNqNbNNoBWxrvNJ9WZLSbdzCH7CpmKdfyh8YWcvfubArVWueH84LhaReQEEX0FDPEU4x4Wvyg"
}
```



In header of all below requests add this
```bash
X-Auth-Token : "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUwNDk4ODUyODM2NiwiZXhwIjoxNTA1NTkzMzI4fQ.YgsC5rRM2-JMuSNqNbNNoBWxrvNJ9WZLSbdzCH7CpmKdfyh8YWcvfubArVWueH84LhaReQEEX0FDPEU4x4Wvyg"
```

###To Create a Post
POST

`http://localhost:3030/api/post`
```json
{
	"userid" : "59b41cc993603c062cb455ed",
	"title" : "First Post",
	"content" : "Content",
	"tags" : ""
}
```

###To Create a Comment
POST
`http://localhost:3030/api/post/comment`
```json
{
	"username":"admin",
	"content":"comment content",
	"tags":"",
	"postId":"59b4461f93603c08978e80a7"
}
```

###To Edit a Post
PUT
`http://localhost:3030/api/post/59b4461f93603c08978e80a7`
```json
{
	"userid" : "59b2f5d337a92d09f15fb9d1",
	"title" : "First Post EDITED",
	"content" : "Content EDITED",
	"tags" : ""
}
```

###To Edit a Comment
PUT
`http://localhost:3030/api/post/comment/59b4461f93603c08978e80a7-0`
```json
{
	"username":"parijat",
	"content":"comment content EDITED",
	"tags":"EDITED",
	"postId":"59b4461f93603c08978e80a7"
}
```
###To See the list of Users
_REQUEST_

GET
`http://localhost:3030/api/user/list`

_RESPONSE_
```json
[
    {
        "fullName": "Admin",
        "username": "admin",
        "password": "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
        "email": "abc@abc.cnd",
        "lastPasswordReset": null,
        "authorities": "ADMIN,ROOT",
        "id": "59b2f5d337a92d09f15fb9d1"
    },
    {
        "fullName": "Parijat Mukherjee",
        "username": "parijat",
        "password": "$2a$10$hVvFD.bz8bu7eooCs6Pg6e0ejuZeNfkZmZ7cjpJqL2X0xHzSvuClW",
        "email": "abc@abc.com.org.in",
        "lastPasswordReset": null,
        "authorities": "USER",
        "id": "59b41cc993603c062cb455ed"
    }
]
```

###To see the list of posts
_REQUEST_ 

GET
`http://localhost:3030/api/post/list`
_RESPONSE_
```json
[
    {
        "_id": "59b4461f93603c08978e80a7",
        "author": {
            "fullName": "Parijat Mukherjee",
            "username": "parijat",
            "password": "$2a$10$hVvFD.bz8bu7eooCs6Pg6e0ejuZeNfkZmZ7cjpJqL2X0xHzSvuClW",
            "email": "abc@abc.com.org.in",
            "lastPasswordReset": null,
            "authorities": "USER",
            "id": "59b41cc993603c062cb455ed"
        },
        "title": "First Post EDITED",
        "content": "Content EDITED",
        "tags": "",
        "createDate": 1504986655781,
        "lastModified": 1504987348042,
        "comments": [
            {
                "id": "59b4461f93603c08978e80a7-0",
                "author": {
                    "fullName": "Admin",
                    "username": "admin",
                    "password": "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
                    "email": "abc@abc.cnd",
                    "lastPasswordReset": null,
                    "authorities": "ADMIN,ROOT",
                    "id": "59b2f5d337a92d09f15fb9d1"
                },
                "content": "comment content EDITED",
                "tags": "EDITED",
                "createDate": 1504987100661,
                "lastModified": 1504987562822,
                "postId": "59b4461f93603c08978e80a7"
            }
        ]
    }
]
```