package com.thompson.spectrumshooter.color;

import java.util.ArrayList;
import java.util.Random;

/**
 * ColorWheel.java
 * 
 * Creates a color wheel that contains all colors on the RGB color wheel
 * excluding tints, shades, and hues. Each position in the
 *
 * @author Christopher Boyer
 *
 * Version: 1.02
 */
public class ColorWheel
{
	// a color at full strength has a value of 255
	private static final int FULL_COLOR = 255;
	// a color at no strength has a value of 0
	private static final int EMPTY_COLOR = 0;

	private ArrayList<Integer> colorWheel; // all the color values of the wheel

	private int colorIncrement;	// the power by which color increments

	private Random ran;

	/**
	 * Generate a new color wheel going from red, to blue, to green, and then
	 * back to red. This works by, starting with red, increasing the blue value
	 * until blue is at maximum. Then decreasing the red value. Repeat the
	 * process for blue to green, and then from green to red.
	 */
	public ColorWheel()
	{
		this.colorIncrement = 1;

		this.colorWheel = new ArrayList<Integer>();

		// start with Red
		int nextRed = 255;
		int nextGreen = 0;
		int nextBlue = 0;

		do {

			// convert RGB values to a decimal value
			int nextColor = (nextRed << 16) | (nextGreen << 8) | nextBlue;

			colorWheel.add(nextColor);

			if (nextRed == FULL_COLOR) {
				if (nextBlue < FULL_COLOR && nextGreen == EMPTY_COLOR) {
					nextBlue = nextBlue + 1;
				} else if (nextBlue == FULL_COLOR && nextGreen == EMPTY_COLOR) {
					nextRed = nextRed - 1;
				} else if (nextGreen <= FULL_COLOR && nextBlue == EMPTY_COLOR) {
					nextGreen = nextGreen - 1;
				}
			} else if (nextBlue == FULL_COLOR) {
				if (nextRed > EMPTY_COLOR  && nextGreen == EMPTY_COLOR){
					nextRed = nextRed - 1;
				} else if (nextGreen < FULL_COLOR && nextRed == EMPTY_COLOR) {
					nextGreen = nextGreen + 1;
				} else if (nextGreen == FULL_COLOR && nextRed == EMPTY_COLOR) {
					nextBlue = nextBlue - 1;
				}
			} else if (nextGreen == FULL_COLOR) {
				if (nextBlue > EMPTY_COLOR && nextRed == EMPTY_COLOR) {
					nextBlue = nextBlue - 1;
				} else if (nextRed < FULL_COLOR && nextBlue == EMPTY_COLOR) {
					nextRed = nextRed + 1;
				}
			}

		// while we're not back at the beginning
		} while (nextRed != FULL_COLOR ||
				 nextGreen !=EMPTY_COLOR ||
				 nextBlue != EMPTY_COLOR);

		ran = new Random();
	}

	/**
	 * Gets a random color code that will fit inside the color wheel.
	 * @return random color code
	 */
	public int random()
	{
		return ran.nextInt(this.colorWheel.size());
	}

	/**
	 * Increment the given color code by the color increment factor. If it goes over
	 * the alloted array of color codes, then return the overflow.
	 * @param currentColorCode the current color code to be incremented
	 * @return the incremented color code
	 */
	public int incrementColorCode(int currentColorCode)
	{
		return (currentColorCode >= colorWheel.size() - colorIncrement)
				? ((currentColorCode + colorIncrement) - colorWheel.size())
				: currentColorCode+colorIncrement;
	}

	/**
	 * Set the factor by which the color code will be incremented.
	 * @param colorIncrement the factor be which the color code will be incremented.
	 */
	public void setColorIncrement(int colorIncrement)
	{
		this.colorIncrement = colorIncrement;
	}

	/**
	 * Get the number of colors inside the color wheel
	 * @return the number of colors inside the color wheel
	 */
	public int getColorCount()
	{
		return colorWheel.size();
	}

	/**
	 * Get a color value based on a color's code
	 * @param colorCode the code of the color desired
	 * @return the value of the desired color
	 */
	public int getColorValue(int colorCode)
	{
		return colorWheel.get(colorCode);
	}

	/**
	 * Get the red value of the color corresponding to the given color code
	 * @param colorCode the color the red is desired of
	 * @return red value of the desired color
	 */
	public int getRedValue(int colorCode)
	{
		return (colorWheel.get(colorCode) & 0xFF0000) >> 16;
	}

	/**
	 * Get the green value of the color corresponding to the given color code
	 * @param colorCode the color the green is desired of
	 * @return green value of the desired color
	 */
	public int getGreenValue(int colorCode)
	{
		return (colorWheel.get(colorCode) & 0x00FF00) >> 8;
	}

	/**
	 * Get the blue value of the color corresponding to the given color code
	 * @param colorCode the color the blue is desired of
	 * @return blue value of the desired color
	 */
	public int getBlueValue(int colorCode)
	{
		return (colorWheel.get(colorCode) & 0x0000FF);
	}

	/**
	 * Get the String of the desired color's hexidecimal value
	 * @param colorCode the code of the desired color
	 * @return String of the desire color's Hexidecimal value
	 */
	public String getHexCode(int colorCode)
	{
		String hexCode = Integer.toHexString(colorWheel.get(colorCode));
		// add the maximum possbile number of leading zeros, then clip it to
		// the needed length
		hexCode = ("000000" + hexCode).substring(hexCode.length());
		hexCode = hexCode.toUpperCase();
		return "#" + hexCode;
	}


}
