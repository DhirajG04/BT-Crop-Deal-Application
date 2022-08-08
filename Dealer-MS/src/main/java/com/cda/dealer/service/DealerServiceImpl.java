package com.cda.dealer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.dealer.exception.ResourceNotFoundException;
import com.cda.dealer.model.Dealer;
import com.cda.dealer.repository.DealerRepository;


@Service
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerRepository dealerRepository;
	
	
	
	@Override
    public Dealer addDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

	
	@Override
	public List<Dealer> getDealers() {
		return this.dealerRepository.findAll();
	}

	
	 @Override
	 public Dealer getDealerById(String dealerId) throws ResourceNotFoundException {

	        Optional < Dealer > dealerData = this.dealerRepository.findById(dealerId);

	        if (dealerData.isPresent()) {
	            return dealerData.get();
	        } else {
	            throw new ResourceNotFoundException("Record not found with id : " + dealerData);
	        }
	    }

	 @Override
	 public Dealer updateDealer(Dealer dealer) throws ResourceNotFoundException {
	        Optional < Dealer > dealerDetails = this.dealerRepository.findById(dealer.getDealerId());

	        if (dealerDetails.isPresent()) {
	        	Dealer dealerUpdate = dealerDetails.get();
	        	dealerUpdate.setDealerId(dealer.getDealerId());
	        	dealerUpdate.setDealerName(dealer.getDealerName());
	        	dealerUpdate.setDealerEmail(dealer.getDealerEmail());
	        	dealerUpdate.setDealerContactNo(dealer.getDealerContactNo());
	        	dealerUpdate.setDealerPass(dealer.getDealerPass());
	        	dealerRepository.save(dealerUpdate);
	            return dealerUpdate;
	        } else {
	            throw new ResourceNotFoundException("Record not found with id : " + dealer.getDealerId());
	        }
	    }


	@Override
    public void deleteDealerById(String dealerId) throws ResourceNotFoundException {
        Optional < Dealer > dealerDetails = this.dealerRepository.findById(dealerId);

        if (dealerDetails.isPresent()) {
            this.dealerRepository.deleteById(dealerId);
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + dealerId);
        }

    }

}


