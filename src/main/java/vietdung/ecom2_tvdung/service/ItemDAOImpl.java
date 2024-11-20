package vietdung.ecom2_tvdung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vietdung.ecom2_tvdung.model.Item;
import vietdung.ecom2_tvdung.repository.ItemRepository;

@Service
public class ItemDAOImpl {
    @Autowired
    private ItemRepository itemDAO;

    public Item getItem(Long id) {
        return itemDAO.findItemById(id);
    }


}
