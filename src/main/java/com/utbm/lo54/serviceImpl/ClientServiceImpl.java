package com.utbm.lo54.serviceImpl;

import java.util.List;

import com.utbm.lo54.bean.Client;
import com.utbm.lo54.bean.Course;
import com.utbm.lo54.dao.ClientJpaDao;
import com.utbm.lo54.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.us.example.bean.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 * @ClassName UserServiceImpl
 * @author abel
 * @date 2016年11月10日
 */
@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientJpaDao clientJpaDao;

    @Override
    public List<Client> getAllClient(){return clientJpaDao.findAllById();}

    @Override
    public Client getClientById(int Id) {return clientJpaDao.findById(Id); }

    @Override
    public Client getClientBy4infos(String nom, String prenom, String address, String telephone) {
       Client client=clientJpaDao.getClientByLastNameAndFirstNameAndAddressAndPhone(nom,prenom,address,telephone);
        if (client==null) return null;
        else return client;
    }

    @Override
    public Client saveClientInfo(Client client) {
        return clientJpaDao.save(client);
    }
    /*
    @Override
    public Client editClientInfo(int Id,String lastname,String firstname,String address,int phone,String email,int course_Session) {
        return clientJpaDao.editClient(Id,lastname,firstname,address,phone,email,course_Session);
    }
    */
    @Override
    public boolean deleteClientById(int Id) {
        return clientJpaDao.deleteById(Id);
    }




}
