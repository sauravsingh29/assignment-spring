/**
 * 
 */
package com.ss.task.dao.shop.details.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.task.shop.details.request.ShopDetails;
import com.ss.task.shop.details.service.ShopDetailsService;

/**
 * @author Saurav Singh
 *
 */
@RestController
@RequestMapping("/shopdetails")
public class ShopDetailsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDetailsController.class);

	@Autowired
	@Qualifier("shopDetailsService")
	private ShopDetailsService shopDetailsService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveShopDetails(@RequestBody ShopDetails shopDetails) {
		LOGGER.debug("Started endpoint method {}, params - {}", "saveShopDetails", shopDetails.toString());
		try {
			int saveCount = shopDetailsService.saveShopDetails(shopDetails);
			if (saveCount == 1) {
				LOGGER.debug("Record inserted!");
			}
		} catch (Exception e) {
			LOGGER.error("Exception {}", e);
			return new ResponseEntity<String>(
					"Unable to add Shop address at this moment! Please check request or try later.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			LOGGER.debug("Finished endpoint method {}", "saveShopDetails");
		}
		return new ResponseEntity<String>("Shop address added!", HttpStatus.OK);
	}
}
