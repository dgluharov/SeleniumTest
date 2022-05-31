Feature: Shop features
  In order to operate with shop
  As as client
  I want options to add and remove items to the cart

  Scenario Outline: Add items to the cart
    Given a user is on the shop page
    And the user adds the following items to the cart:
      | Item 1  | Item 2  | Item 3  |
      | <Item1> | <Item2> | <Item3> |
    When the user navigates to the cart
    Then all items are successfully added

    Examples:
      | Item1                     | Item2                        | Item3       |
      | Android Quick Start Guide | Functional Programming in JS | HTML5 Forms |
      | Android Quick Start Guide | HTML5 Forms                  |             |
      | Android Quick Start Guide |                              |             |
#      | items                                                                |
#      | Android Quick Start Guide, Functional Programming in JS, HTML5 Forms |
#      | Android Quick Start Guide, Functional Programming in JS              |
#      | Android Quick Start Guide                                            |

  Scenario Outline: Add items to the cart - cart link is updated
    Given a user is on the shop page
    When the user adds the following items to the cart:
      | Item 1  | Item 2  | Item 3  |
      | <Item1> | <Item2> | <Item3> |
    Then the cart link is properly updated

    Examples:
      | Item1                     | Item2                        | Item3       |
      | Android Quick Start Guide | Functional Programming in JS | HTML5 Forms |
      | Android Quick Start Guide | HTML5 Forms                  |             |
      | Android Quick Start Guide |                              |             |



