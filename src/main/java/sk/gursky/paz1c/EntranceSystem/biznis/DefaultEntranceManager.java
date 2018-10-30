package sk.gursky.paz1c.EntranceSystem.biznis;

import java.util.List;

import sk.gursky.paz1c.EntranceSystem.persistent.CardReader;
import sk.gursky.paz1c.EntranceSystem.persistent.DaoFactory;
import sk.gursky.paz1c.EntranceSystem.persistent.User;
import sk.gursky.paz1c.EntranceSystem.persistent.UserDao;

public class DefaultEntranceManager implements EntranceManager {

	UserDao userDao = DaoFactory.INSTANCE.getUserDao();
	
	@Override
	public boolean validate(String chipId, CardReader cr) {
		List<User> users = userDao.getAll();
		User user = null;
		for (User u : users) {
			if (u.getChipId().equals(chipId)) {
				user = u;
				break;
			}
		}
		if (user != null && user.isActive() && user.hasAccess(cr))
			return true;
		return false;
	}

	@Override
	public void deactivate(String chipId) {
		for(User u: userDao.getAll()) {
			if (chipId.equals(u.getChipId())) {
				u.setActive(false);
				userDao.save(u);
			}
		} 		
	}

}
