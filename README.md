# Restful Test
The Application has a seeder to add 5 task and 1 TODO list. 

**Heroku API:** https://sauli-test.herokuapp.com/todo-list
## API
### localhost:8080/todo-list
#### GET
Response (Status 200)
```
[{
    "id": 1,
    "name": "TODO List 1",
    "description": "Description"
},
...
...
,{
    "id": n,
    "name": "TODO List n",
    "description": "Description"
}]
```
#### GET /{id}
Response (Status 200)
```
{
  "id": {id},
  "name": "TODO List name",
  "description": "TODO list description"
}
```
#### POST
Request Body
```
{
  "name": "TODO List name",
  "description": "TODO list description"
}
```
Response (Status 201)
```
{
  "id": 1,
  "name": "TODO List name",
  "description": "TODO list description"
}
```
#### PUT /{id}
Request Body
```
{
  "name": "TODO List name",
  "description": "TODO list description"
}
```
Response (Status 200)
```
{
  "id": {id}
  "name": "TODO List name",
  "description": "TODO list description"
}
```
#### DELETE /{id}
Response (Status 200)
```
{
  "id": {id},
  "name": "TODO List name",
  "description": "TODO list description"
}
```
### localhost:8080/todo-list/{todoId}/task
#### GET
Response (Status 200)
```
[{
    "todo_id": {todoId},
    "id": 1,
    "name": "TODO List 1",
    "description": "Description"
},
...
...
,{
    "todo_id": {todoId},
    "id": n,
    "name": "TODO List n",
    "description": "Description"
}]
```
#### GET /todo-list/{todoId}/task/{id}
Response (Status 200)
```
{
    "todo_id": {todoId},
    "id": {id},
    "name": "TODO List 1",
    "description": "Description"
}
```
#### POST /todo-list/{todoId}/task
Request Body
```
{
    "name": "TODO List 1",
    "description": "Description"
}
```
Response (Status 201)
```
{
  "todo_id": {todoId},
  "id": 1,
  "name": "TODO List 1",
  "description": "Description"
}
```
#### PUT /todo-list/{todoId}/task/{id}
Request Body
```
{
    "name": "TODO List 1",
    "description": "Description"
}
```
Response (Status 201)
```
{
  "todo_id": {todoId},
  "id": 1,
  "name": "TODO List 1",
  "description": "Description"
}
```
#### DELETE /todo-list/{todoId}/task/{id}
Response (Status 200)
```
{
  "todo_id": {todoId},
  "id": 1,
  "name": "TODO List 1",
  "description": "Description"
}
