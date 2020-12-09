package Service;

import Dao.AddressDao;
import Model.Address;
import Exception.UnknownCityException;
import Exception.UnknownAddressException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService{

    private final AddressDao addressDao;

    @Override
    public Collection<Address> getAllAddresses() {
        return addressDao.readAll();
    }

    @Override
    public void recordAddress(Address address) throws UnknownCityException {
        addressDao.createAddress(address);
    }

    @Override
    public void updateFirstMatch(Address originalAddress, Address updatedAddress) throws UnknownCityException, UnknownAddressException {
        addressDao.updateFirstMatch(originalAddress, updatedAddress);
    }

    @Override
    public void deleteAddress(Address address) throws UnknownCityException, UnknownAddressException {
        addressDao.deleteAddress(address);
    }
}
