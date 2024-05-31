"use client"
import { useState } from "react"
import { useCookies } from 'next-client-cookies';
import apiService from "../services/apiService";

interface IPokemon {
    name: string,
    sprite: string,
    abilities: { name: string }[]
}

const Pokemon = () => {
    const [pokemonName, setPokemonName] = useState("")
    const [pokemon, setPokemon] = useState<IPokemon | null>(null)
    const cookies = useCookies();

    const submitValues = async () => {
        try {
            const response = await apiService.get(`/pokemon/${pokemonName}`, {
                headers: {
                    "Authorization": `Bearer ${cookies.get("token")}`
                }
            })
            if (response.status === 200) {
                setPokemon(response.data)
            }
        }
        catch (error) {
            window.alert("Pokemon não encontrado")
        }
    }

    return (
        <main className="flex min-h-screen items-center justify-center">
            <div className="flex flex-col w-full h-full items-center justify-center p-12">
                <div className="flex flex-col w-full h-full items-center justify-center">
                    <div className="p-20 w-1/2 bg-slate-300">
                        <div className="flex justify-center w-full p-2">
                            <label htmlFor="pokemon">Pokemon: </label>
                            <input
                                id="pokemon"
                                type="text"
                                onChange={event => setPokemonName(event.target.value)}
                                value={pokemonName}
                            />
                        </div>
                        <div className="flex justify-center w-full p-2">
                            <button className="border-2 border-black w-1/2 bg-white" onClick={submitValues}>Enviar</button>
                        </div>
                    </div>
                    {pokemon &&
                        <div>
                            <div>Nome: {pokemon.name}</div>
                            <img src={pokemon.sprite} />
                            <div>Abilities:
                                <div>
                                    {pokemon.abilities.map((ability, index) => <p key={index}>{index+1}. {ability.name}</p>)}
                                </div>
                            </div>
                        </div>
                    }
                </div>
            </div>
        </main>
    );
}
export default Pokemon