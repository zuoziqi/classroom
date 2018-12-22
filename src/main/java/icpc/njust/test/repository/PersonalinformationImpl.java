package icpc.njust.test.repository;

import icpc.njust.test.table.PersonalinformationEntity;

import java.util.List;

/**
 * Created by DELL on 2018/12/22.
 */
public class PersonalinformationImpl implements PersonalinformationDao {
    @Override
    public void addinfo(String id, String name, String phone, String email, String school, String academy, String identity) {

    }

    @Override
    public void deleteinfo(String id) {

    }

    @Override
    public List<PersonalinformationEntity> showall() {
        return null;
    }

    @Override
    public PersonalinformationEntity findinfo(String id) {
        return null;
    }

    @Override
    public void changeinfo(String id, String name, String phone, String email, String school, String academy, String identity) {

    }
}
