package pl.coderslab;

import pl.coderslab.DAO.UserDao;
import pl.coderslab.model.User;

public class Main {

    public static void main(String[] args) {

       /* User newUser = new User("admin","admin@com","admin");
        System.out.println(newUser.toString());*/

        UserDao userDao = new UserDao();
        //userDao.create(newUser);
        //System.out.println(newUser.toString());*/

        //User redUser = userDao.read(1);
//        System.out.println(redUser);
//
//       // User nonUser = userDao.read(1000);
//       // System.out.println(nonUser);
//
//        redUser.setUserName("new username");
//        userDao.update(redUser);
//
//        User updatedUser = userDao.read(1);
//        System.out.println(updatedUser);
//
//        User tobedeleted = userDao.read(1);
//        System.out.println(tobedeleted);
//
//        userDao.delete(tobedeleted.getId());
//
//        User deleted = userDao.read(1);
//        System.out.println(deleted);

        for (int i = 0; i < 5; i++){
            userDao.create(
                    new User("user"+i,"email"+i,"haslo"));
        }

        User[] users = userDao.findAll();
        for (User user: users){
            System.out.println(user);
        }
    }
}
