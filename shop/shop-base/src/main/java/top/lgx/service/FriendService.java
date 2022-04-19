package top.lgx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lgx.dao.FriendDao;
import top.lgx.dao.NoFriendDao;
import top.lgx.dao.UserDao;
import top.lgx.entity.Friend;
import top.lgx.entity.NoFriend;

import javax.transaction.Transactional;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-19 16:37
 * @Description:
 */
@Service
public class FriendService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Transactional(rollbackOn = Exception.class)
    public int addFriend(String userId, String friendId) {
        if (friendDao.selectCount(userId, friendId) > 0) {
            return 0;
        }
        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setIsLike("0");
        friendDao.save(friend);

        if (friendDao.selectCount(friendId, userId) > 0) {
            friendDao.updateLike(userId, friendId, "1");
            friendDao.updateLike(friendId, userId, "1");
        }
        userDao.incFansCount(friendId, 1);
        userDao.incFollowCount(userId, 1);
        return 1;
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-04-19 16:52
     * @Params: userId
     * @Params: friendId
     * @Return: void
     * @Description: 向不喜欢列表中添加记录
     */
    public void addNoFriend(String userId, String friendId) {
        NoFriend noFriend = new NoFriend();
        noFriend.setUserId(userId);
        noFriend.setFriendId(friendId);
        noFriendDao.save(noFriend);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteFriend(String userId, String friendId) {
        friendDao.deleteFriend(userId, friendId);
        friendDao.updateLike(friendId, userId, "0");
        userDao.incFollowCount(userId, -1);
        userDao.incFansCount(friendId, -1);
        addNoFriend(userId, friendId);
    }
}
