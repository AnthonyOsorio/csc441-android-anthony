1. My first build took me around 20 minutes.

2. When toggling dark mode the background of the settings app, google, my own app, as well as screen hud bars changed to darker colors.

3. One thing that I don't understand is why the files are organized the way that they currently are and how they correspond with one another.
---
1. The padding number was changed from 24 to 50. This alteration cause the text and picture to shift a little more towards the right of the screen.
---
1. Counter not broken, Logcat lines n/a.

2. Count changed but the screen didn't as some data involved within the display was not saved

3. Remember stores the value recorded allowing for its aggregation within the screen during its rebuilding, this would not occur without it, making the display show inaccurate information that was not saved. 
---
1. The message you would receive is the "Too short — at least 3 characters"
2. My app needs this rule as there aren't files that contain numerical values.
3.
| I typed | What the app did | Correct? |
___
| 8888 | Name cant be only numbers message | Yes |
___
| /// | Add button greyed out | Yes |
___
| 77 | Too short message | Yes |
___
| OOOOO | Add button greyed out | Yes |
___
| /// | Add button greyed out | Yes |
___
| tttttttttttttttttttttttttttttt | Add button greyed out | Yes |
___
| #### | Add button greyed out | Yes |
___
| oooo | Add button greyed out | Yes |
___
After rotating, I had still been on the list screen, but the files I had created on the main page did not popped up. As for the difference in currentScreen and Traits. currentScreen creates an observable state value that can change, while traits stores data within a modifiable list.
___
Week 13, Wednesday: Lab 21
1. What triggers refresh() when InterestsScreen first opens?
 - LaunchedEffect(Unit) triggers onRefresh() when the screen first opens.

2. What triggers refresh() when the button is tapped?
 - The Refresh button has onClick = onRefresh, which calls refresh() when tapped.
3. Why does InterestsScreen receive onRefresh instead of the ViewModel?
 - The screen handles the user interface, while the ViewModel handles data and application logic. Passing onRefresh keeps the screen independent of the ViewModel.
4. What is the difference between a successful [] response and a network failure?
 - A successful [] response means the API request worked, but the database returned no records. A network failure means the request could not successfully reach or communicate with the API.
5. How did you prove Android and the website use the same Supabase data? 
 - By checking that both applications retrieve records from the same Supabase project and showcase_items table. The records displayed in Android and on the website can be compared by their titles, descriptions, and categories to verify that they match.
