package com.openclassrooms.safetynet.model;

import lombok.*;
import lombok.Data;


/**
 * The type Firestation.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Firestation {

	@NonNull
	private int station;
	@NonNull
	private String address;

}
