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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Data structure for Load Balancer based on
 * Quantum proposal http://wiki.openstack.org/LBaaS/CoreResourceModel/proposal 
 * 
 * @author KC Wang
 */

@JsonSerialize(using=LBMemberSerializer.class)
public class LBMember implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private int address;
    private short port;
    private String macString;
    
    private int connectionLimit;
    private short adminState;
    private short status;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public short getPort() {
		return port;
	}

	public void setPort(short port) {
		this.port = port;
	}

	public String getMacString() {
		return macString;
	}

	public void setMacString(String macString) {
		this.macString = macString;
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

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String poolId;
    private String vipId;
    
    public LBMember() {
        id = String.valueOf((int) (Math.random()*10000));
        address = 0;
        macString = null;
        port = 0;
        
        connectionLimit = 0;
        adminState = 0;
        status = 0;
        poolId = null;
        vipId = null;
    }
}
