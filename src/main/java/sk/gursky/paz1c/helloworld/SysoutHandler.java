package sk.gursky.paz1c.helloworld;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SysoutHandler implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Stlačilo sa tlačidlo");
		
	}

}
