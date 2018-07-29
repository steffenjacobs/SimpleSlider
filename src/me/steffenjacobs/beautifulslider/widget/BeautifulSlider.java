package me.steffenjacobs.beautifulslider.widget;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/** @author Steffen Jacobs */
public class BeautifulSlider extends Composite {

	private static BeautifulSliderUiBinder uiBinder = GWT.create(BeautifulSliderUiBinder.class);

	

	interface BeautifulSliderUiBinder extends UiBinder<Widget, BeautifulSlider> {
	}

	@UiField
	DivElement divBox;
	@UiField
	DivElement divLeft;
	
	final BeautifulSliderEventListener divEventListener;

	public BeautifulSlider() {
		initWidget(uiBinder.createAndBindUi(this));

		final SliderEventHandler graphicsHandler = new SliderEventHandler() {

			@Override
			public void onDragEnd(NativeEvent event) {
				setPosition((((double) event.getClientX() - divBox.getAbsoluteLeft()) / divBox.getClientWidth()) * 100);
			}

			@Override
			public void onDrag(NativeEvent event) {
				setPosition((((double) event.getClientX() - divBox.getAbsoluteLeft()) / divBox.getClientWidth()) * 100);
			}

			@Override
			public void onClick(NativeEvent event) {
				new SlideAnimation(divLeft, (double) event.getClientX() - divBox.getAbsoluteLeft()).run(100);
			}
		};

		divEventListener = new BeautifulSliderEventListener();
		divEventListener.addEventHandler(graphicsHandler);
		DOM.sinkEvents(divBox, Integer.MAX_VALUE);
		DOM.setEventListener(divBox, divEventListener);
	}

	public void setPosition(double percent) {
		divLeft.getStyle().setWidth(divBox.getOffsetWidth() * (percent / 100), Unit.PX);
	}

	public double getPosition() {
		return ((double) divLeft.getClientWidth() / divBox.getClientWidth()) * 100;
	}

	public void addEventHandler(SliderEventHandler listener) {
		divEventListener.addEventHandler(listener);
	}
	
	private class SlideAnimation extends Animation {
		private final Element element;
		private final double targetWidth;
		private final double startWidth;

		public SlideAnimation(Element element, double targetWidth) {
			this.element = element;
			this.targetWidth = targetWidth;
			this.startWidth = element.getClientWidth();
		}

		@Override
		protected void onComplete() {
			element.getStyle().setWidth(targetWidth, Unit.PX);
		}

		@Override
		protected void onUpdate(double progress) {
			element.getStyle().setWidth(startWidth + progress * (targetWidth - startWidth), Unit.PX);
		}
	}

	private class BeautifulSliderEventListener implements EventListener {

		boolean isDragging = false;
		boolean dragged = false;
		boolean dragJustEnded = false;

		private final List<SliderEventHandler> handlers = new LinkedList<>();

		private BeautifulSliderEventListener() {
			Event.addNativePreviewHandler(event -> {
				switch (event.getNativeEvent().getType()) {
				case "mouseup":
					mouseUp(event.getNativeEvent());
					break;
				case "mousemove":
					mouseMove(event.getNativeEvent());
					break;
				default:
					break;
				}
			});
		}

		@Override
		public void onBrowserEvent(Event event) {
			switch (event.getType()) {
			case "click":
				if (dragJustEnded) {
					dragJustEnded = false;
				} else {
					handlers.forEach(h -> h.onClick(event));
				}
				break;
			case "mousemove":
				mouseMove(event);
				break;
			case "mousedown":
				isDragging = true;
				break;
			case "mouseup":
				mouseUp(event);
				break;
			default:
				break;
			}
		}

		private void mouseUp(NativeEvent event) {
			isDragging = false;
			if (dragged) {
				dragged = false;
				dragJustEnded = true;
				handlers.forEach(h -> h.onDragEnd(event));
			}
		}

		private void mouseMove(NativeEvent event) {
			if (isDragging) {
				if (!dragged) {
					dragged = true;
					handlers.forEach(h -> h.onDragStart(event));
				}
				handlers.forEach(h -> h.onDrag(event));
			}
		}

		public void addEventHandler(SliderEventHandler handler) {
			handlers.add(handler);
		}
	}

}