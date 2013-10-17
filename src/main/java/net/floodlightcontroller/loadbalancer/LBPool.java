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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

/**
 * Data structure for Load Balancer based on
 * Quantum proposal http://wiki.openstack.org/LBaaS/CoreResourceModel/proposal 
 * 
 * @author KC Wang
 */


@JsonSerialize(using=LBPoolSerializer.class)
public class LBPool implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String tenantId;
    private String netId;
    private short lbMethod;
    private byte protocol;
    private ArrayList<String> members;
    private ArrayList<String> monitors;
    private short adminState;
    private short status;
    
    private String vipId;
    
    private int previousMemberIndex;
    
    public LBPool() {
        id = String.valueOf((int) (Math.random()*10000));
        name = null;
        tenantId = null;
        netId = null;
        lbMethod = 0;
        protocol = 0;
        members = new ArrayList<String>();
        monitors = new ArrayList<String>();
        adminState = 0;
        status = 0;
        previousMemberIndex = -1;
    }
    
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

	public short getLbMethod() {
		return lbMethod;
	}

	public void setLbMethod(short lbMethod) {
		this.lbMethod = lbMethod;
	}

	public byte getProtocol() {
		return protocol;
	}

	public void setProtocol(byte protocol) {
		this.protocol = protocol;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}

	public ArrayList<String> getMonitors() {
		return monitors;
	}

	public void setMonitors(ArrayList<String> monitors) {
		this.monitors = monitors;
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

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public int getPreviousMemberIndex() {
		return previousMemberIndex;
	}

	public void setPreviousMemberIndex(int previousMemberIndex) {
		this.previousMemberIndex = previousMemberIndex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LBPool(LBPool pool) {
    	this.id = pool.id; 
    	this.name = pool.name; 
    	this.tenantId = pool.tenantId; 
    	this.netId = pool.netId; 
    	this.lbMethod = pool.lbMethod; 
    	this.protocol = pool.protocol; 
    	this.members = new ArrayList<String>() ; 
    	for (String s : pool.members){
    		this.members.add(s);
    	}
    	this.monitors = new ArrayList<String>(); 
    	for (String s : pool.monitors){
    		this.members.add(s);
    	}
    	this.adminState = pool.adminState; 
    	this.status = pool.status; 
    	this.previousMemberIndex = pool.previousMemberIndex; 
    	this.vipId = pool.vipId; 
    }
    
    public String pickMember(IPClient client) {
        // simple round robin for now; add different lbmethod later
        if (members.size() > 0) {
            previousMemberIndex = (previousMemberIndex + 1) % members.size();
            return members.get(previousMemberIndex);
        } else {
            return null;
        }
    }

}
