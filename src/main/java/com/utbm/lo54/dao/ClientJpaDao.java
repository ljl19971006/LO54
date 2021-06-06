package com.utbm.lo54.dao;

import com.utbm.lo54.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientJpaDao extends JpaRepository<Client, Long> {
    //查找所有
    @Query(value = "select * from client",nativeQuery = true)
    public List<Client> findAllById();

    //通过client的id查找
    @Query(name = "findById",value = "select * from client where client.Id = ?1",nativeQuery = true)
    public Client findById(int Id);

    //新增或修改client信息
    @Query(name = "saveClient",value = "insert INTO client (Id,lastName,firstName,address,phone,email,course_Session_Id1,course_Session_Id2) " +
            "values (#{Id},#{lastname},#{fisrtname},#{address},#{phone},#{email},#{course_Session_Id1},#{course_Session_Id2})" +
            "ON DUPLICATE KEY UPDATE lastName = #{lastname},fisrtName = #{fisrtname},address = #{address},phone = #{phone},email = #{email}," +
            "course_Session_Id1 =#{course_Session_Id1},course_Session_Id2 =#{course_Session_Id2};",nativeQuery = true)
    public Client saveClient(Client client);

    /*修改client信息
    @Query(name = "editClient",value = "UPDATE client set lastname = ?2,fisrtname = ?3,address = ?4,phone = ?5,email = ?6," +
            "course_Session = ?7 where id = ?1;" ,nativeQuery = true)
    public Client editClient(int Id,String lastname,String firstname,String address, int phone,String email,int course_Session);
    */
    @Query(value = "select * from client c where lastName = #{prenom} and firstName = #{nom} and address",nativeQuery = true)
    public Client getClientByLastNameAndFirstNameAndAddressAndPhone(String nom,String prenom,String address,String phone);

    //删除client信息
    @Query(name = "deleteById",value = "delete from client where Id=#{Id};",nativeQuery = true)
    public boolean deleteById(int Id);

}