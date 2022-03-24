package com.openclassrooms.safetynet.service.firestation;

import java.util.List;

public interface IFirestation {
	public void addFirestation();
	public void updateFirestation();
	public void deleteFirestation();
	public List<String> getPeopleByFirestationNumber(int station);
}
