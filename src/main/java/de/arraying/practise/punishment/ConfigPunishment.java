package de.arraying.practise.punishment;

import de.arraying.practise.Practise;

import java.util.List;

/**
 * Copyright 2018 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public final class ConfigPunishment {

    private final String name;
    private final String description;
    private final int slot;
    private final PunishmentType.Representation[] ladder;

    /**
     * Creates a new config punishment.
     * @param key The key.
     */
    public ConfigPunishment(String key) {
        this.name = getFrom(key, "name").toString();
        this.description = getFrom(key, "description").toString();
        this.slot = (int) getFrom(key, "slot");
        this.ladder = ((List<String>) getFrom(key, "ladder")).stream()
                .map(it -> {
                    String[] parts = it.split(",");
                    return new PunishmentType.Representation(
                            PunishmentType.valueOf(parts[0].toUpperCase()),
                            parts.length > 1 ? Long.valueOf(parts[1]) : Punishment.PERMANENT
                    );
                })
                .toArray(PunishmentType.Representation[]::new);
    }

    /**
     * Gets a value from the config.
     * @param key The key.
     * @param field The field.
     * @return The value.
     */
    private Object getFrom(String key, String field) {
        return Practise.getPractise().getConfig().get("punishments." + key + "." + field);
    }

}
