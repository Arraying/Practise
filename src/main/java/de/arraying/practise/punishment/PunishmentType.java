package de.arraying.practise.punishment;

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
public enum PunishmentType {

    /**
     * A kick.
     */
    KICK,

    /**
     * A mute.
     */
    MUTE,

    /**
     * A ban.
     */
    BAN;

    /**
     * A punishment representation.
     */
    public static final class Representation {

        private final PunishmentType type;
        private final long duration;

        /**
         * Creates a punishment representation.
         * @param type The type of the punishment.
         * @param duration The duration in seconds.
         */
        public Representation(PunishmentType type, long duration) {
            this.type = type;
            this.duration = duration;
        }

    }

}
