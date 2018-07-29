package me.steffenjacobs.beautifulslider.client;

import me.steffenjacobs.beautifulslider.widget.BeautifulSlider;
import me.steffenjacobs.beautifulslider.widget.SliderEventHandler;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.ui.RootPanel;

public class BeautifulSliderExample implements EntryPoint {

	public void onModuleLoad() {

		// create a slider and add it to the root panel
		final BeautifulSlider slider = new BeautifulSlider();
		RootPanel.get("sliderDiv").add(slider);

		// set the slider position to 33
		Scheduler.get().scheduleDeferred(() -> slider.setPosition(33));

		// add an event handler to the slider and log events to console
		final SliderEventHandler debugHandler = new SliderEventHandler() {
			@Override
			public void onClick(NativeEvent event) {
				GWT.log("on-click");
			}

			@Override
			public void onDragStart(NativeEvent event) {
				GWT.log("on-drag-start");
			}

			@Override
			public void onDrag(NativeEvent event) {
				GWT.log("on-drag");
			}

			@Override
			public void onDragEnd(NativeEvent event) {
				GWT.log("on-drag-end");
			}
		};
		slider.addEventHandler(debugHandler);
	}
}
