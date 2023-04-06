package org.home.knowledge.appstore;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AppStoreApplication {

	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(AppStoreApplication.class, args);
	}

	/**
	 * Logs urls of interest on start up
	 * 
	 * @param event
	 */
	@EventListener
	void onApplicationEvent(ApplicationStartedEvent event) {
		try {
			var address = InetAddress.getLocalHost().getHostName();
			var addressBaseUrl = "http://" + address + ":" + serverPort;

			log.info("Web server ready and waiting, good luck out there!\n"
					+ "\n"
					+ "\n  End points of interest:"
					+ "\n               home -> " + addressBaseUrl + "/server-home.html"
					+ "\n             health -> " + addressBaseUrl + "/actuator/health"
					+ "\n    Spring Actuator -> " + addressBaseUrl + "/actuator"
					+ "\n"
					+ "\n"
					+ "\n    front end -> " + addressBaseUrl
					+ " (will require that an index.html or controller be created to point to front end)"
					+ "\n"
					+ "\n");
		} catch (Exception e) {
			var msg = "Exception occurred while building url to display in console on startup";
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}
	}

}
