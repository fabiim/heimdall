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
	protected String id;
    protected String name;
    protected String tenantId;
    protected String netId;
    protected short lbMethod;
    protected byte protocol;
    ArrayList<String> members;
    private ArrayList<String> monitors;
    protected short adminState;
    protected short status;
    
    protected String vipId;
    
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
