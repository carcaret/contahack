/**
 * 
 */
package com.hackaton.psd2.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackaton.psd2.dao.model.CredentialInfo;

/**
 * @author cesarrodriguezmedina
 *
 */
public interface CredentialInfoRepository extends CrudRepository<CredentialInfo, String> {
	
	public CredentialInfo findOneByUid(String uid);

}
