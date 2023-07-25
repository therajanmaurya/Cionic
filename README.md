# Cionic Android Homework
The goal of this project is to write a simple app to download and display feed items containing the text "optio".

Requirements:
Kotlin should be used
fetch content from https://jsonplaceholder.typicode.com/posts, example response:
    [
      {
        "userId": 1,
        "id": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
      },
      {
        "userId": 1,
        "id": 2,
        "title": "qui est esse",
        "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
      },
      ...
    ]

display the title and body (variable height multiline if possible for each) in each row
rows should have alternating background colors
touching a row should display a dialog with the title and body
use Swipe-to-Refresh to allow refreshing data from the server
use the Fuel library (optional) or your favorite third party library to download/parse JSON (add the following to build.gradle):
    implementation 'com.github.kittinunf.fuel:fuel-android:2.3.1'
    implementation 'com.github.kittinunf.fuel:fuel-json:2.3.1'

If you choose to further improve the user interface or use different 3rd party modules, please let us know why you made those changes.

## Designs
<table>
  <tr>
    <td><img src="https://raw.githubusercontent.com/therajanmaurya/Cionic/master/art/home.jpg"></td>
    <td><img src="https://raw.githubusercontent.com/therajanmaurya/Cionic/master/art/dialog.jpg"></td>
  </tr>
</table>
