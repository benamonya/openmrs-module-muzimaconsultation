/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.muzimaconsultation.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.muzima.model.NotificationData;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Write brief description about the class here.
 */
public class NotificationDataConverter {

    private final Log log = LogFactory.getLog(NotificationDataConverter.class);

    public static Map<String, Object> convert(final NotificationData notificationData) {
        Map<String, Object> converted = new HashMap<String, Object>();
        if (notificationData != null) {
            converted.put("uuid", notificationData.getUuid());
            converted.put("payload", notificationData.getPayload());
            converted.put("status", notificationData.getStatus());
            converted.put("source", notificationData.getSource());
            converted.put("subject", notificationData.getSubject());

            Person sender = notificationData.getSender();
            Map<String, Object> senderObject = new HashMap<String, Object>();
            senderObject.put("uuid", sender.getUuid());
            senderObject.put("name", sender.getPersonName().getFullName());
            converted.put("sender", senderObject);

            Person recipient = notificationData.getReceiver();
            if (recipient != null) {
                Map<String, Object> recipientObject = new HashMap<String, Object>();
                recipientObject.put("uuid", recipient.getUuid());
                recipientObject.put("name", recipient.getPersonName().getFullName());
                converted.put("recipient", recipientObject);
            }

            Role role = notificationData.getRole();
            if (role != null) {
                Map<String, Object> roleObject = new HashMap<String, Object>();
                roleObject.put("uuid", role.getUuid());
                roleObject.put("name", role.getRole());
                converted.put("role", roleObject);
            }

            Patient patient = notificationData.getPatient();
            if (patient != null) {
                Map<String, Object> patientObject = new HashMap<String, Object>();
                patientObject.put("uuid", patient.getUuid());
                patientObject.put("name", patient.getPersonName().getFullName());
                converted.put("patient", patientObject);
            }

            String datetime = Context.getDateFormat().format(notificationData.getDateCreated());
            converted.put("datetime", datetime);
        }
        return converted;
    }
}
