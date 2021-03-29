# Alarm Kata

Alarm Kata in Java.

## Difficulty

Easy. Good for teaching: Introducing tests, Mocking, Refactoring

## Problem Description

### Stage 1 - Safety net

`Alarm` class is part of a much bigger legacy system. Since there's been a 
lot of bugs in the past related to this code, your task is to introduce tests
to this part of system (`Alarm` class). 

### Stage 2 - Refactoring

Refactor `Alarm` class until you are satisfied with design. 
Keep in mind that `Alarm` class is used by other parts of legacy system
(that is not presented here), so try not to break contract by changing 
behavior or interface.

### Stage 3 - Introduce SMS notifications

New feature request! Some clients want to be notified via SMS when alarm goes off.
Introduce `SmsService` with the same interface as `EmailService` and invoke it
together with `EmailService`.

### Stage 4 - Introduce web hooks for automations

Alarm feature gets very popular with users! We are getting more and more requests
to introduce web hooks that users can use for automating their workflows.
Introduce `WebHookService` with the same interface as `EmailService` and `SmsService`,
and invoke it together with previous two.