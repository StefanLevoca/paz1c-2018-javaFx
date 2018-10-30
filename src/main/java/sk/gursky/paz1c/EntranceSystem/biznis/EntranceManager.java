package sk.gursky.paz1c.EntranceSystem.biznis;

import sk.gursky.paz1c.EntranceSystem.persistent.CardReader;

public interface EntranceManager {
	boolean validate(String chipId, CardReader cr);
	void deactivate(String chipId);
}
