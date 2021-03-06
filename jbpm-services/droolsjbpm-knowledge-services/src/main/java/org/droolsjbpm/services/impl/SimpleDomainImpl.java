/*
 * Copyright 2012 JBoss by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.droolsjbpm.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.droolsjbpm.services.api.Domain;
import org.kie.commons.java.nio.file.Path;

/**
 *
 * @author salaboy
 */
public class SimpleDomainImpl implements Domain{
    
    private Long id;
    
    private String name;
    
    // Asset Name / Assets Definition Path 
    private Map<String, String> assetsDefs = new HashMap<String, String>();
    
    // Ksession Name / List of process assets Paths
    private Map<String, List<Path>> ksessionProcessDefinitions = new HashMap<String, List<Path>>();
    
    // Ksession Name / List of rules assets Paths
    private Map<String, List<Path>> ksessionRulesDefinitions = new HashMap<String, List<Path>>();
    
    // Process Id (String) / Process Content.. (?? this is really needed??)
    private Map<String, Map<String,String>> processes = new HashMap<String, Map<String,String>>();
    
    private Map<String, String> ksessionRepositoryRoot = new HashMap<String, String>();
    
    private Long parentId;
    
    public SimpleDomainImpl() {
    }

    public SimpleDomainImpl(String name) {
        this.name = name;
        
    }

    public SimpleDomainImpl(String name, long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<String, String> getAssetsDefs() {
        return assetsDefs;
    }

    public void setAssetsDefs(Map<String, String> assetsDefs) {
        this.assetsDefs = assetsDefs;
    }
    
    public void addAsset(String name, String path){
        this.assetsDefs.put(name, path);
    }

    
    @Override
    public Map<String, List<Path>> getProcessDefinitionFromKsession() {
        return ksessionProcessDefinitions;
    }

    @Override
    public void setProcessDefinitionToKsessions(Map<String, List<Path>> ksessionProcessDefinitions) {
        this.ksessionProcessDefinitions = ksessionProcessDefinitions;
    }
    
    @Override
    public void addProcessDefinitionToKsession(String ksession, Path path){
        if(this.ksessionProcessDefinitions.get(ksession) == null){
            this.ksessionProcessDefinitions.put(ksession, new ArrayList<Path>());
        }
        this.ksessionProcessDefinitions.get(ksession).add(path);   
    }
    
    @Override
    public Map<String, List<Path>> getRulesDefinitionFromKsession() {
        return ksessionRulesDefinitions;
    }

    @Override
    public void setRulesDefinitionToKsessions(Map<String, List<Path>> ksessionRulesDefinitions) {
        this.ksessionRulesDefinitions = ksessionRulesDefinitions;
    }
    
    @Override
    public void addRulesDefinitionToKsession(String ksession, Path path){
        if(this.ksessionRulesDefinitions.get(ksession) == null){
            this.ksessionRulesDefinitions.put(ksession, new ArrayList<Path>());
        }
        this.ksessionRulesDefinitions.get(ksession).add(path);
        
    }
    

    @Override
    public void addProcessBPMN2ContentToKsession(String sessionName, String processId, String bpmn2Content) {
        if(processes.get(sessionName) == null){
            processes.put(sessionName, new HashMap<String, String>());
        }
        processes.get(sessionName).put(processId, bpmn2Content);
    }

    @Override
    public Map<String, String> getAllProcesses() {
        Map<String, String> allProcesses = new HashMap<String, String>();
        for(String sessionName : processes.keySet()){
            allProcesses.putAll(processes.get(sessionName));
        }
        return allProcesses;
    }

    @Override
    public Map<String, String> getProcessesBySession(String ksessionName) {
        return processes.get(ksessionName);
    }

    @Override
    public String getProcessDefinitionBPMN2(String ksessionName, String processId) {
        return processes.get(ksessionName).get(processId);
    }

    public Map<String, String> getKsessionRepositoryRoot() {
        return ksessionRepositoryRoot;
    }
    
    public void addKsessionRepositoryRoot(String ksession, String ksessionRepositoryRoot) {
        this.ksessionRepositoryRoot.put(ksession, ksessionRepositoryRoot);
    }

    public void setKsessionRepositoryRoot(Map<String, String> ksessionRepositoryRoot) {
        this.ksessionRepositoryRoot = ksessionRepositoryRoot;
    }

 
}
