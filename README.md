# Refactoring Kata Test

## Solution

Here are the steps I followed to solve this Kata :

1.Separate the code into multiple classes and layers (SoC , SRP)

2.Ideally , i would had to start by covering all the code with integration tests before refactoring , but too many scenarios have discouraged me...

3.I removed the commented code (violate YAGNI) + meaningful naming + hard typing using enums

4.I adopted the same commit style as the initial commits (ex : refactor: message)

5.A controller advice is used for exception handling

6.It's weired that PriceTooHighException triggers a BadRequest ! but this behaviour has been preserved so the contract
is not broken.

## Improvements left

1.Add unit tests/integration tests (i ran out of time :P , no TDD LoL )

2.The solution lacks a Domain/Model layer , a kind of Shopping & Item entities have to be used
in the service layer + Mappers (using MapStruct maybe)

3.For money calculation the BigDecimal should be used instead of Double (why not wrapping it inside a Money class ? )

4.Use lombok + add some logging
...
