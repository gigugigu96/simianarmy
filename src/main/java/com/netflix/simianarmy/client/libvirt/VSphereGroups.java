package com.netflix.simianarmy.client.libvirt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.autoscaling.model.AutoScalingGroup;
import com.amazonaws.services.autoscaling.model.Instance;

/*
 *  Copyright 2012 Immobilienscout GmbH
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */
/**
 * Wraps the creation and grouping of Instance's in AutoScalingGroup's
 * 
 * @author ingmar.krusch@immobilienscout24.de
 */
class VSphereGroups {
    final private Map<String, AutoScalingGroup> map;

    public VSphereGroups() {
        map = new HashMap<String, AutoScalingGroup>();
    }

    public List<AutoScalingGroup> asList() {
        return new ArrayList<AutoScalingGroup>(map.values());
    }

    public void addInstance(final String instanceId, final String groupName) {
        AutoScalingGroup asg = map.get(groupName);
        if (asg == null) {
            asg = new AutoScalingGroup();
            asg.setAutoScalingGroupName(groupName);
            map.put(groupName, asg);
        }
        List<Instance> instances = asg.getInstances();
        Instance instance = new Instance();
        instance.setInstanceId(instanceId);
        instances.add(instance);
    }
}
