package com.utbm.lo54.service;

import com.utbm.lo54.bean.Course;
import com.utbm.lo54.bean.Client;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {

    public List<Client> getAllClient();

    public Client getClientById(int Id);

    public Client saveClientInfo(Client client);
    /*
    public Client editClientInfo(int Id,String lastname,String firstname,String address,int phone,String email,int course_Session);
    */
    public boolean deleteClientById(int Id);


}
