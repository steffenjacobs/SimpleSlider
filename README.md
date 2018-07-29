# SimpleSlider
A simple Slider implemented in native GWT with divs. It supports setting the position, adding/removing event handlers, setting size and changing the bar color.

## Screenshot
This is how the slider looks like:

![Slider Image](./slider.gif?raw=true "The Simple Slider")

When you click on the slider, the position is changed. Additionally, you can drag with the mouse to set a specified position. There is a small 100ms-animation when you set a new position on the slider via clicking. During this animation, the slider moves to the new position.

## Try it out
1) Download SimpleSlider.war from the [release page](https://github.com/steffenjacobs/SimpleSlider/releases/tag/1.0.0).
2) Download the [Jetty Runner](https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-runner/9.4.11.v20180605) jar file.
3) With Java installed, execute the command: *java -jar jetty-runner-9.4.11.v20180605.jar ./SimpleSlider.war*
