package me.steffenjacobs.simpleslider.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import me.steffenjacobs.simpleslider.widget.SimpleSlider;
import me.steffenjacobs.simpleslider.widget.SliderEventHandler;

public class SimpleSliderExample implements EntryPoint {
	private static final NumberFormat nf = NumberFormat.getFormat("#0.00");

	public void onModuleLoad() {

		// create a slider and add it to the root panel
		final SimpleSlider slider = new SimpleSlider();
		RootPanel.get("sliderDiv").add(slider);

		// add a label to show the position of the slider
		final Label lab = new Label("33.00%");
		RootPanel.get("sliderDiv").add(lab);

		// set the slider position to 33
		Scheduler.get().scheduleDeferred(() -> slider.setPosition(33));

		// add an event handler to the slider and log events to console
		final SliderEventHandler debugHandler = new SliderEventHandler() {
			@Override
			public void onClick(NativeEvent event) {
				GWT.log("on-click");
				lab.setText(nf.format(slider.getPosition()) + "%");
			}

			@Override
			public void onDragStart(NativeEvent event) {
				GWT.log("on-drag-start");
				lab.setText(nf.format(slider.getPosition()) + "%");
			}

			@Override
			public void onDrag(NativeEvent event) {
				GWT.log("on-drag");
				lab.setText(nf.format(slider.getPosition()) + "%");
			}

			@Override
			public void onDragEnd(NativeEvent event) {
				GWT.log("on-drag-end");
				lab.setText(nf.format(slider.getPosition()) + "%");
			}
		};
		slider.addEventHandler(debugHandler);

		// set some properties of thes slider
		slider.setWidth(500);
		slider.setHeight(10);
		slider.setBarColor("#e2ffb7");
	}
}
