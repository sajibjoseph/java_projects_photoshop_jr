# Photoshop Jr

This project involves implementing various image filters in Java for a simplified version of Photoshop. The filters are designed to work on images with even dimensions, simplifying the implementation of certain filters like `scaleDown`.

Key aspects include:
- If your filter isn't altering the image, check the terminal window in BlueJ for any errors.
- When you call a filter/function from another filter/function, you must replace the original picture with the returned version.
- Remember to extract the files before opening the project in BlueJ and to compress your entire photoshop-jr folder when submitting it to Google Classroom.
- The `ImageFilters` class is where all your code goes. There is a static method for each filter; you just fill in the code.
- The `RUN_THIS_FILE` class contains the main method to start the program.
- The `Picture` and `Pixel` classes have their own documentation. Remember that red, green, and blue values must be between 0 and 255. To change a pixel's color, you need to use `getPixel()` to get the pixel and `setPixel()` to apply the changes.
- If your filter isn't altering the image, check the terminal window in BlueJ for any errors.
- When you call a filter/function from another filter/function, you must replace the original picture with the returned version.
- Remember to extract the files before opening the project in BlueJ and to compress your entire photoshop-jr folder when submitting it to Google Classroom.

The `ImageFilters` class is where all your code goes. There is a static method for each filter; you just fill in the code. The `RUN_THIS_FILE` class contains the main method to start the program.

The `Picture` and `Pixel` classes have their own documentation. Remember that red, green, and blue values must be between 0 and 255. To change a pixel's color, you need to use `getPixel()` to get the pixel and `setPixel()` to apply the changes.

You are required to implement various filters such as `flipHorizontal`, `flipVertical`, `toGrayscale`, `zombieVision`, `scaleUp`, `scaleDown`, `mirrorVertical`, `mirrorHorizontal`, `rotateRight`, `rotateLeft`, `shiftRight`, `shiftLeft`, `detectEdges`, `pixelate`, and `blackAndWhiteComic`. Some of these filters require you to call other filters/functions, so plan your implementation order accordingly.

Please note that all fields are private and all methods and constructors are public as per good object-oriented design principles. Feel free to contribute and raise any issues. Happy coding!
