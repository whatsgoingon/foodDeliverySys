package com.xjj.foodDeliveryServer.bean;

public class SellerUserPair {
	private Seller seller;
	private User user;
	
	public SellerUserPair(Seller seller, User user) {
		super();
		this.seller = seller;
		this.user = user;
	}
//	public Seller getSeller() {
//		return seller;
//	}
//	public void setSeller(Seller seller) {
//		this.seller = seller;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerUserPair other = (SellerUserPair) obj;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
