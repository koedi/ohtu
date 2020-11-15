Feature: Nonexistent user cannot login

    Scenario: nonexistent user can not login to
        Given command login is selected
        When  username "akkep" and password "akkep" are entered
        Then  system will respond with "wrong username or password"
