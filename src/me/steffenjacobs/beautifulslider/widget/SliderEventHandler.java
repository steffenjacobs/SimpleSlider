package me.steffenjacobs.beautifulslider.widget;

import com.google.gwt.dom.client.NativeEvent;

/** @author Steffen Jacobs */
public interface SliderEventHandler {
	default void onClick(NativeEvent event) {
	}

	default void onDragStart(NativeEvent event) {
	}

	default void onDrag(NativeEvent event) {
	}

	default void onDragEnd(NativeEvent event) {
	}
}