Feature: User cannot login with invalid password

    Scenario: user can not login with incorrect password
        Given command login is selected
        When  username "pekka" and password "pekka" are entered
        Then  system will respond with "wrong username or password"
