
# Tempo Backend Challenge

Tempo one Backend Challenge built with Kotlin


## Solution Approach
- The provided endpoint was inspected to understand the structure. After appropriate set up, the webclient was set up to enable REST calls within the application.
- The database SEEDER was implemented for the predefined roles. The seeder handles the migration of the predefined roles.
- Webclient module for REST calls was implemented.
- Roles models, TeamRole model was also designed. Each model has it own Data transfer object.
- For edges cases handling a composite key was done for `userId` and `teamId` this prevents same user to be added to same team in multiples. Sending same user and team as a payload will only assign the user to a new role without creating any new record.
- A user can only be assigned to only one role.
## API Reference

#### Post Team Roles
- assign role to user in a team
- The provided endpoint is called as a validation of user and teams data.

```http
  POST /teamrole
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `userId` | `string` | **Required**. User ID |
| `teamId` | `string` | **Required**. Team ID |
| `roleId` | `string` |  Role ID (default is used if it is not supplied) |


Sample payload
```
{
    "roleId":1,
    "teamId":"29191298-248d-48d7-8a6a-0bfecbc75f14",
    "userId":"fa1529de-5f20-49a7-ad25-a494008dd322"
}
```

Response

```
{
    "roleId": 1,
    "userId": "fa1529de-5f20-49a7-ad25-a494008dd322",
    "teamId": "29191298-248d-48d7-8a6a-0bfecbc75f14",
    "user": {
        "id": "fa1529de-5f20-49a7-ad25-a494008dd322",
        "firstName": "Jaren",
        "lastName": "Kerluke",
        "displayName": "jarenKerluke",
        "avatarUrl": "https://cdn.fakercloud.com/avatars/victorstuber_128.jpg",
        "location": "New Felixtown"
    },
    "role": {
        "id": 1,
        "name": "Developer"
    },
    "team": {
        "id": "29191298-248d-48d7-8a6a-0bfecbc75f14",
        "name": "Old Brown Flamingo"
    }
}
```





## Documentation
Other API request

[Documentation](https://documenter.getpostman.com/view/5081938/2s83zdwmS8)


## Deployment

To deploy this project run
- clone the repo and navigate to root folder

```bash
  git clone https://github.com/akinyeleolat/tempo-challenge.git
  cd tempo-challenge
```
- build the project

```bash
  gradle clean build
```
- build docker image

```bash
  docker build . -t challenge-demo    
```
- run the dockerfile

```bash
  docker run -p 8080:8080 challenge-demo
```
you can test on postmam
```
http://localhost:8080/teamrole
```




## Running Tests

To run tests, run the following command

```bash
  gradle clean build
  gradle test
```


## Tech Stack

**Server:** Gradle, Kotlin, Springboot, JUnit5


## Authors

- [@Akinyele Oluwatosin](https://github.com/akinyeleolat)

