
POST http://localhost:9002/clients

{
    "clientId": "client",
    "clientSecret": "secret",
    "clientName": "myAuthCodeClient",
    "requiredProofKey": false,
    "grantType": "authorization_code",
    "tokenFormat": "self_contained",
    "redirectUri": "http://localhost:8081"
}