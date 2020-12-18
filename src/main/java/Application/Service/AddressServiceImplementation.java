package Application.Service;

import Application.Dao.AddressDao;
import Application.Exception.UnknownCountryException;
import Application.Model.Address;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownAddressException;
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
    public void recordAddress(Address address) throws UnknownCountryException, UnknownCityException {
        addressDao.createAddress(address);
    }

    @Override
    public void updateFirstMatch(Address originalAddress, Address updatedAddress) throws UnknownCityException, UnknownAddressException, UnknownCountryException {
        addressDao.updateFirstMatch(originalAddress, updatedAddress);
    }

    @Override
    public void deleteAddress(Address address) throws UnknownCityException, UnknownAddressException, UnknownCountryException {
        addressDao.deleteAddress(address);
    }
}
