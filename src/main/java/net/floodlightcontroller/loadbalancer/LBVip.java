/**
 *    Copyright 2013, Big Switch Networks, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License"); you may
 *    not use this file except in compliance with the License. You may obtain
 *    a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *    License for the specific language governing permissions and limitations
 *    under the License.
 **/

package net.floodlightcontroller.loadbalancer;

import java.io.Serializable;
import java.util.ArrayList;

import net.floodlightcontroller.util.MACAddress;




import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

/**
 * Data structure for Load Balancer based on
 * Quantum proposal http://wiki.openstack.org/LBaaS/CoreResourceModel/proposal 
 * 
 * @author KC Wang
 */

@JsonSerialize(using=LBVipSerializer.class)
public class LBVip implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public byte getProtocol() {
		return protocol;
	}

	public void setProtocol(byte protocol) {
		this.protocol = protocol;
	}

	public short getLbMethod() {
		return lbMethod;
	}

	public void setLbMethod(short lbMethod) {
		this.lbMethod = lbMethod;
	}

	public short getPort() {
		return port;
	}

	public void setPort(short port) {
		this.port = port;
	}

	public ArrayList<String> getPools() {
		return pools;
	}

	public void setPools(ArrayList<String> pools) {
		this.pools = pools;
	}

	public boolean isSessionPersistence() {
		return sessionPersistence;
	}

	public void setSessionPersistence(boolean sessionPersistence) {
		this.sessionPersistence = sessionPersistence;
	}

	public int getConnectionLimit() {
		return connectionLimit;
	}

	public void setConnectionLimit(int connectionLimit) {
		this.connectionLimit = connectionLimit;
	}

	public short getAdminState() {
		return adminState;
	}

	public void setAdminState(short adminState) {
		this.adminState = adminState;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public MACAddress getProxyMac() {
		return proxyMac;
	}

	public void setProxyMac(MACAddress proxyMac) {
		this.proxyMac = proxyMac;
	}

	public static String getLB_PROXY_MAC() {
		return LB_PROXY_MAC;
	}

	public static void setLB_PROXY_MAC(String lB_PROXY_MAC) {
		LB_PROXY_MAC = lB_PROXY_MAC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String id;    
    private String name;
    private String tenantId;
    private String netId;
    private int address;
    private byte protocol;
    private short lbMethod;
    private short port;
    private ArrayList<String> pools;
    private boolean sessionPersistence;
    private int connectionLimit;
    private short adminState;
    private short status;
    
    protected MACAddress proxyMac;
    
    public static String LB_PROXY_MAC= "12:34:56:78:90:12";
    
    public LBVip() {
        this.id = String.valueOf((int) (Math.random()*10000));
        this.name = null;
        this.tenantId = null;
        this.netId = null;
        this.address = 0;
        this.protocol = 0;
        this.lbMethod = 0;
        this.port = 0;
        this.pools = new ArrayList<String>();
        this.sessionPersistence = false;
        this.connectionLimit = 0;
        this.address = 0;
        this.status = 0;
        
        this.proxyMac = MACAddress.valueOf(LB_PROXY_MAC);
    }
    
    /**
	 * @param lbVip
	 */
	public LBVip(LBVip lbVip) {
		this.id = lbVip.id; 
		this.name = lbVip.name; 
		this.tenantId = lbVip.tenantId; 
		this.netId = lbVip.netId; 
		this.address = lbVip.address;
		this.adminState = lbVip.adminState; 
		this.protocol = lbVip.protocol; 
		this.lbMethod = lbVip.lbMethod; 
		this.port = lbVip.port; 
		this.pools = Lists.newArrayList(lbVip.pools);
		this.sessionPersistence = lbVip.sessionPersistence; 
		this.connectionLimit = lbVip.connectionLimit; 
		this.address = lbVip.address; 
		this.status = lbVip.status; 
		this.proxyMac = lbVip.proxyMac; 
	}

	public String pickPool(IPClient client) {
        // for now, return the first pool; consider different pool choice policy later
        if (pools.size() > 0)
            return pools.get(0);
        else
            return null;
    }

}
