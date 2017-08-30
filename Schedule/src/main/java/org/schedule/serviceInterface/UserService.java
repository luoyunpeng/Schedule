package org.schedule.serviceInterface;

public interface UserService {
	
    void create(int  id, String name);
    
    void deleteByName(String name);
    
    int getAllUsers();
    
    void deleteAllUsers();
     
    void createUserTable();
}