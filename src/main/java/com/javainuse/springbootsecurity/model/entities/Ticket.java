package com.javainuse.springbootsecurity.model.entities;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ticket Class for tickets management 05/08/2021
 *
 * @author Monica
 * @version 1.0
 */

@Data
@NoArgsConstructor
@JsonRootName("Ticket")
public class Ticket {
	/**
	 * The credit card.
	 */
	private String creditCard;

	/**
	 * The event id.
	 */
	private int eventId;

	/**
	 * The user id.
	 */
	private int userId;


}
