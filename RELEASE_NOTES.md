## 0.0.1-SNAPSHOT
* Created skeleton for bot

## 0.1.0-SNAPSHOT
* Added Command Pattern for bot
Implemented commands:
* start
* stop
* help
* unknown
* no_command

Added Unit Tests:
* SendBotMessage
* CommandContainer

## 0.2.0-SNAPSHOT
CSTB3: Added Docker

## 0.3.0-SNAPSHOT
CSTB4: Added Repository Level
Implemented commands:
* stat

## 0.4.0-SNAPSHOT
User is added by chatId and UserName (@name style)

## 0.5.0-SNAPSHOT
Added to User:
* nickname
* state

Added CallbackQuery
Added functionality: if no callback or command was, checks for value in user state field. If state == command, runs it

## 0.5.1-SNAPSHOT
Refactoring if CustomQuizBot class

## 0.6.0-SNAPSHOT
Moved to postgres
Added functionality to save user statistics

## 0.7.0
Implemented functionality to look through statistics
Implemented ability to delete given answers and start over 

## 0.7.1
Added .env file () 