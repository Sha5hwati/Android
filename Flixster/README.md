# Flixster
Flixster is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#). The app uses the heterogeneous RecyclerView to display the movies playing in the theaters now.

# Features
- [x] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

- [x] Views should be responsive for both landscape/portrait mode.
   - [x] In portrait mode, the poster image, title, and movie overview is shown.
   - [x] In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [x] Display a nice default placeholder graphic for each image during loading.
- [x] Improved the user interface by experimenting with styling and coloring.
- [x] For popular movies (i.e. a movie with more than 7 average vote), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed.
- [x] Added an app icon

# App Walkthough GIF

<img src="./video-walthrough-portrait.gif" width=350 height=450> <img src="./video-walthrough-landscape.gif" width=450 height=230><br>
 
# Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids